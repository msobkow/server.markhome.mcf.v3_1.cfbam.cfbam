// Description: Java 25 Table Object implementation for DelDep.

/*
 *	server.markhome.mcf.CFBam
 *
 *	Copyright (c) 2016-2026 Mark Stephen Sobkow
 *	
 *	Mark's Code Fractal 3.1 CFBam - Business Application Model
 *	
 *	This file is part of Mark's Code Fractal CFBam.
 *	
 *	Mark's Code Fractal CFBam is available under dual commercial license from
 *	Mark Stephen Sobkow, or under the terms of the GNU General Public License,
 *	Version 3 or later.
 *	
 *	Mark's Code Fractal CFBam is free software: you can redistribute it and/or
 *	modify it under the terms of the GNU General Public License as published by
 *	the Free Software Foundation, either version 3 of the License, or
 *	(at your option) any later version.
 *	
 *	Mark's Code Fractal CFBam is distributed in the hope that it will be useful,
 *	but WITHOUT ANY WARRANTY; without even the implied warranty of
 *	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *	GNU General Public License for more details.
 *	
 *	You should have received a copy of the GNU General Public License
 *	along with Mark's Code Fractal CFBam.  If not, see <https://www.gnu.org/licenses/>.
 *	
 *	If you wish to modify and use this code without publishing your changes,
 *	or integrate it with proprietary code, please contact Mark Stephen Sobkow
 *	for a commercial license at mark.sobkow@gmail.com
 *	
 */

package server.markhome.mcf.v3_1.cfbam.cfbamobj;

import java.math.*;
import java.sql.*;
import java.text.*;
import java.time.*;
import java.util.*;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.text.StringEscapeUtils;
import server.markhome.mcf.v3_1.cflib.*;
import server.markhome.mcf.v3_1.cflib.dbutil.*;
import server.markhome.mcf.v3_1.cfsec.cfsec.*;
import server.markhome.mcf.v3_1.cfint.cfint.*;
import server.markhome.mcf.v3_1.cfsec.cfsecobj.*;
import server.markhome.mcf.v3_1.cfint.cfintobj.*;
import server.markhome.mcf.v3_1.cfbam.cfbam.*;

public class CFBamDelDepTableObj
	implements ICFBamDelDepTableObj
{
	protected ICFBamSchemaObj schema;
	protected static int runtimeClassCode = ICFBamDelDep.CLASS_CODE;
	protected static final int backingClassCode = ICFBamDelDep.CLASS_CODE;
	private Map<CFLibDbKeyHash256, ICFBamDelDepObj> members;
	private Map<CFLibDbKeyHash256, ICFBamDelDepObj> allDelDep;
	private Map< ICFBamScopeByTenantIdxKey,
		Map<CFLibDbKeyHash256, ICFBamDelDepObj > > indexByTenantIdx;
	private Map< ICFBamDelDepByDefSchemaIdxKey,
		Map<CFLibDbKeyHash256, ICFBamDelDepObj > > indexByDefSchemaIdx;
	private Map< ICFBamDelDepByDelDepIdxKey,
		Map<CFLibDbKeyHash256, ICFBamDelDepObj > > indexByDelDepIdx;
	public static String TABLE_NAME = "DelDep";
	public static String TABLE_DBNAME = "del_dep";

	public CFBamDelDepTableObj() {
		schema = null;
		members = new HashMap<CFLibDbKeyHash256, ICFBamDelDepObj>();
		allDelDep = null;
		indexByTenantIdx = null;
		indexByDefSchemaIdx = null;
		indexByDelDepIdx = null;
	}

	public CFBamDelDepTableObj( ICFBamSchemaObj argSchema ) {
		schema = (ICFBamSchemaObj)argSchema;
		members = new HashMap<CFLibDbKeyHash256, ICFBamDelDepObj>();
		allDelDep = null;
		indexByTenantIdx = null;
		indexByDefSchemaIdx = null;
		indexByDelDepIdx = null;
	}
	
	/**
	 *	Get class code always returns the runtime class code for the objects, which is not stable until the application is done initializing and registering its objects.
	 *
	 *	@return runtime classcode
	 */ 
	@Override
	public int getClassCode() {
		return CFBamDelDepTableObj.getRuntimeClassCode();
	}	

	/**
	 *	Get the backing store schema's class code, which is hard-coded into the object hierarchy.
	 *
	 *	@return The hardcoded backing store class code for this object, which is only valid in that schema.
	 */
	public static int getBackingClassCode() {
		return( backingClassCode );
	}

	/**
	 *	Get the runtime class code for this table; this value is only stable after the application is fully initialized.
	 *
	 *	@return runtimeClassCode
	 */
	public static int getRuntimeClassCode() {
		return( runtimeClassCode );
	}

	/**
	 *	Set the runtime class code for this table; this is done only during application initialization by the SchemaObj's <tt>initClassCodes()</tt> static method,
	 *	which will only set the class codes once and never again.  Once set, the class codes are immutable within the application.
	 *	Application programmers should never invoke this method, so it has package access only.
	 *
	 *	@param	argNewClassCode	The runtime class code to be used by clients and integrated application logic to identify this table of this schema.
	 */
	static void setRuntimeClassCode(int argNewClassCode ) {
		if (argNewClassCode <= 0) {
			throw new CFLibArgumentUnderflowException(CFBamDelDepTableObj.class, "setRuntimeClassCode", 1, "argNewClassCode", argNewClassCode, 1);
		}
		runtimeClassCode = argNewClassCode;
	}

	@Override
	public ICFBamSchemaObj getSchema() {
		return( schema );
	}

	@Override
	public void setSchema( ICFBamSchemaObj value ) {
		schema = (ICFBamSchemaObj)value;
	}

	@Override
	public String getTableName() {
		return( TABLE_NAME );
	}

	@Override
	public String getTableDbName() {
		return( TABLE_DBNAME );
	}

	@Override
	public Class getObjQualifyingClass() {
		return( ICFBamSchemaDefObj.class );
	}


	@Override
	public void minimizeMemory() {
		allDelDep = null;
		indexByTenantIdx = null;
		indexByDefSchemaIdx = null;
		indexByDelDepIdx = null;
		List<ICFBamDelDepObj> toForget = new LinkedList<ICFBamDelDepObj>();
		ICFBamDelDepObj cur = null;
		Iterator<ICFBamDelDepObj> iter = members.values().iterator();
		while( iter.hasNext() ) {
			cur = iter.next();
			toForget.add( cur );
		}
		iter = toForget.iterator();
		while( iter.hasNext() ) {
			cur = iter.next();
			cur.forget();
		}
	}
	/**
	 *	If your implementation subclasses the objects,
	 *	you'll want to overload the constructByClassCode()
	 *	implementation to return your implementation's
	 *	instances instead of the base implementation.
	 *
	 *	This is the sole factory for instances derived from
	 *	CFBamDelDepObj.
	 */
	@Override
	public ICFBamDelDepObj newInstance() {
		ICFBamDelDepObj inst = new CFBamDelDepObj( schema );
		return( inst );
	}

	/**
	 *	If your implementation subclasses the objects,
	 *	you'll want to overload the constructByClassCode()
	 *	implementation to return your implementation's
	 *	instances instead of the base implementation.
	 *
	 *	This is the sole factory for instances derived from
	 *	CFBamDelDepObj.
	 */
	@Override
	public ICFBamDelDepEditObj newEditInstance( ICFBamDelDepObj orig ) {
		ICFBamDelDepEditObj edit = new CFBamDelDepEditObj( orig );
		return( edit );
	}

	@Override
	public ICFBamDelDepObj realiseDelDep( ICFBamDelDepObj Obj ) {
		ICFBamDelDepObj obj = Obj;
		CFLibDbKeyHash256 pkey = obj.getPKey();
		ICFBamDelDepObj keepObj = null;
		if( members.containsKey( pkey ) && ( null != members.get( pkey ) ) ) {
			ICFBamDelDepObj existingObj = members.get( pkey );
			keepObj = existingObj;

			/*
			 *	We always rebind the data because if we're being called, some index has
			 *	been updated and is refreshing it's data, which may or may not have changed
			 */

			// Detach object from alternate and duplicate indexes, leave PKey alone

			if( indexByTenantIdx != null ) {
				ICFBamScopeByTenantIdxKey keyTenantIdx =
					schema.getCFBamBackingStore().getFactoryScope().newByTenantIdxKey();
				keyTenantIdx.setRequiredTenantId( keepObj.getRequiredTenantId() );
				Map<CFLibDbKeyHash256, ICFBamDelDepObj > mapTenantIdx = indexByTenantIdx.get( keyTenantIdx );
				if( mapTenantIdx != null ) {
					indexByTenantIdx.remove( keyTenantIdx );
				}
			}

			if( indexByDefSchemaIdx != null ) {
				ICFBamDelDepByDefSchemaIdxKey keyDefSchemaIdx =
					schema.getCFBamBackingStore().getFactoryDelDep().newByDefSchemaIdxKey();
				keyDefSchemaIdx.setOptionalDefSchemaId( keepObj.getOptionalDefSchemaId() );
				Map<CFLibDbKeyHash256, ICFBamDelDepObj > mapDefSchemaIdx = indexByDefSchemaIdx.get( keyDefSchemaIdx );
				if( mapDefSchemaIdx != null ) {
					mapDefSchemaIdx.remove( keepObj.getPKey() );
					if( mapDefSchemaIdx.size() <= 0 ) {
						indexByDefSchemaIdx.remove( keyDefSchemaIdx );
					}
				}
			}

			if( indexByDelDepIdx != null ) {
				ICFBamDelDepByDelDepIdxKey keyDelDepIdx =
					schema.getCFBamBackingStore().getFactoryDelDep().newByDelDepIdxKey();
				keyDelDepIdx.setRequiredRelationId( keepObj.getRequiredRelationId() );
				Map<CFLibDbKeyHash256, ICFBamDelDepObj > mapDelDepIdx = indexByDelDepIdx.get( keyDelDepIdx );
				if( mapDelDepIdx != null ) {
					mapDelDepIdx.remove( keepObj.getPKey() );
					if( mapDelDepIdx.size() <= 0 ) {
						indexByDelDepIdx.remove( keyDelDepIdx );
					}
				}
			}
			// Keep passing the new object because it's the one with the record
			// that the base table needs to copy to the existing object from
			// the cache.
			keepObj = (ICFBamDelDepObj)schema.getScopeTableObj().realiseScope( Obj );

			// Attach new object to alternate and duplicate indexes -- PKey stay stable

			if( indexByTenantIdx != null ) {
				ICFBamScopeByTenantIdxKey keyTenantIdx =
					schema.getCFBamBackingStore().getFactoryScope().newByTenantIdxKey();
				keyTenantIdx.setRequiredTenantId( keepObj.getRequiredTenantId() );
				Map<CFLibDbKeyHash256, ICFBamDelDepObj > mapTenantIdx = indexByTenantIdx.get( keyTenantIdx );
				if( mapTenantIdx != null ) {
					mapTenantIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByDefSchemaIdx != null ) {
				ICFBamDelDepByDefSchemaIdxKey keyDefSchemaIdx =
					schema.getCFBamBackingStore().getFactoryDelDep().newByDefSchemaIdxKey();
				keyDefSchemaIdx.setOptionalDefSchemaId( keepObj.getOptionalDefSchemaId() );
				Map<CFLibDbKeyHash256, ICFBamDelDepObj > mapDefSchemaIdx = indexByDefSchemaIdx.get( keyDefSchemaIdx );
				if( mapDefSchemaIdx != null ) {
					mapDefSchemaIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByDelDepIdx != null ) {
				ICFBamDelDepByDelDepIdxKey keyDelDepIdx =
					schema.getCFBamBackingStore().getFactoryDelDep().newByDelDepIdxKey();
				keyDelDepIdx.setRequiredRelationId( keepObj.getRequiredRelationId() );
				Map<CFLibDbKeyHash256, ICFBamDelDepObj > mapDelDepIdx = indexByDelDepIdx.get( keyDelDepIdx );
				if( mapDelDepIdx != null ) {
					mapDelDepIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( allDelDep != null ) {
				allDelDep.put( keepObj.getPKey(), keepObj );
			}
		}
		else {
			keepObj = obj;
			keepObj = (ICFBamDelDepObj)schema.getScopeTableObj().realiseScope( keepObj );

			// Attach new object to PKey, all, alternate, and duplicate indexes
			members.put( keepObj.getPKey(), keepObj );
			if( allDelDep != null ) {
				allDelDep.put( keepObj.getPKey(), keepObj );
			}

			if( indexByTenantIdx != null ) {
				ICFBamScopeByTenantIdxKey keyTenantIdx =
					schema.getCFBamBackingStore().getFactoryScope().newByTenantIdxKey();
				keyTenantIdx.setRequiredTenantId( keepObj.getRequiredTenantId() );
				Map<CFLibDbKeyHash256, ICFBamDelDepObj > mapTenantIdx = indexByTenantIdx.get( keyTenantIdx );
				if( mapTenantIdx != null ) {
					mapTenantIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByDefSchemaIdx != null ) {
				ICFBamDelDepByDefSchemaIdxKey keyDefSchemaIdx =
					schema.getCFBamBackingStore().getFactoryDelDep().newByDefSchemaIdxKey();
				keyDefSchemaIdx.setOptionalDefSchemaId( keepObj.getOptionalDefSchemaId() );
				Map<CFLibDbKeyHash256, ICFBamDelDepObj > mapDefSchemaIdx = indexByDefSchemaIdx.get( keyDefSchemaIdx );
				if( mapDefSchemaIdx != null ) {
					mapDefSchemaIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByDelDepIdx != null ) {
				ICFBamDelDepByDelDepIdxKey keyDelDepIdx =
					schema.getCFBamBackingStore().getFactoryDelDep().newByDelDepIdxKey();
				keyDelDepIdx.setRequiredRelationId( keepObj.getRequiredRelationId() );
				Map<CFLibDbKeyHash256, ICFBamDelDepObj > mapDelDepIdx = indexByDelDepIdx.get( keyDelDepIdx );
				if( mapDelDepIdx != null ) {
					mapDelDepIdx.put( keepObj.getPKey(), keepObj );
				}
			}

		}
		return( keepObj );
	}

	@Override
	public ICFBamDelDepObj createDelDep( ICFBamDelDepObj Obj ) {
		ICFBamDelDepObj obj = Obj;
		ICFBamDelDep rec = obj.getDelDepRec();
		schema.getCFBamBackingStore().getTableDelDep().createDelDep(
			null,
			rec );
		obj.copyRecToPKey();
		if( obj.getClassCode() == runtimeClassCode ) {
			obj = (ICFBamDelDepObj)(obj.realise());
		}
		obj.endEdit();
		return( obj );
	}

	@Override
	public ICFBamDelDepObj readDelDep( CFLibDbKeyHash256 pkey ) {
		return( readDelDep( pkey, false ) );
	}

	@Override
	public ICFBamDelDepObj readDelDep( CFLibDbKeyHash256 pkey, boolean forceRead ) {
		ICFBamDelDepObj obj = null;
		if( ( ! forceRead ) && members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		else {
			ICFBamDelDep readRec = schema.getCFBamBackingStore().getTableDelDep().readDerivedByIdIdx( null,
						pkey );
			if( readRec != null ) {
				obj = (ICFBamDelDepObj)schema.getScopeTableObj().constructByClassCode( readRec.getClassCode() );
				obj.setPKey( readRec.getPKey() );
				obj.setRec( readRec );
				obj = (ICFBamDelDepObj)obj.realise();
			}
		}
		return( obj );
	}

	@Override
	public ICFBamDelDepObj readCachedDelDep( CFLibDbKeyHash256 pkey ) {
		ICFBamDelDepObj obj = null;
		if( members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		return( obj );
	}

	@Override
	public void reallyDeepDisposeDelDep( ICFBamDelDepObj obj )
	{
		final String S_ProcName = "CFBamDelDepTableObj.reallyDeepDisposeDelDep() ";
		String classCode;
		if( obj == null ) {
			return;
		}
		CFLibDbKeyHash256 pkey = obj.getPKey();
		ICFBamDelDepObj existing = readCachedDelDep( pkey );
		if( existing == null ) {
			return;
		}
		members.remove( pkey );
		ICFBamDelDepByDefSchemaIdxKey keyDefSchemaIdx = schema.getCFBamBackingStore().getFactoryDelDep().newByDefSchemaIdxKey();
		keyDefSchemaIdx.setOptionalDefSchemaId( existing.getOptionalDefSchemaId() );

		ICFBamDelDepByDelDepIdxKey keyDelDepIdx = schema.getCFBamBackingStore().getFactoryDelDep().newByDelDepIdxKey();
		keyDelDepIdx.setRequiredRelationId( existing.getRequiredRelationId() );



		if( indexByDefSchemaIdx != null ) {
			if( indexByDefSchemaIdx.containsKey( keyDefSchemaIdx ) ) {
				indexByDefSchemaIdx.get( keyDefSchemaIdx ).remove( pkey );
				if( indexByDefSchemaIdx.get( keyDefSchemaIdx ).size() <= 0 ) {
					indexByDefSchemaIdx.remove( keyDefSchemaIdx );
				}
			}
		}

		if( indexByDelDepIdx != null ) {
			if( indexByDelDepIdx.containsKey( keyDelDepIdx ) ) {
				indexByDelDepIdx.get( keyDelDepIdx ).remove( pkey );
				if( indexByDelDepIdx.get( keyDelDepIdx ).size() <= 0 ) {
					indexByDelDepIdx.remove( keyDelDepIdx );
				}
			}
		}


		schema.getScopeTableObj().reallyDeepDisposeScope( obj );
	}
	@Override
	public void deepDisposeDelDep( CFLibDbKeyHash256 pkey ) {
		ICFBamDelDepObj obj = readCachedDelDep( pkey );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public ICFBamDelDepObj lockDelDep( CFLibDbKeyHash256 pkey ) {
		ICFBamDelDepObj locked = null;
		ICFBamDelDep lockRec = schema.getCFBamBackingStore().getTableDelDep().lockDerived( null, pkey );
		if( lockRec != null ) {
				locked = (ICFBamDelDepObj)schema.getScopeTableObj().constructByClassCode( lockRec.getClassCode() );
			locked.setRec( lockRec );
			locked.setPKey( lockRec.getPKey() );
			locked = (ICFBamDelDepObj)locked.realise();
		}
		else {
			throw new CFLibCollisionDetectedException( getClass(), "lockDelDep", pkey );
		}
		return( locked );
	}

	@Override
	public List<ICFBamDelDepObj> readAllDelDep() {
		return( readAllDelDep( false ) );
	}

	@Override
	public List<ICFBamDelDepObj> readAllDelDep( boolean forceRead ) {
		final String S_ProcName = "readAllDelDep";
		if( ( allDelDep == null ) || forceRead ) {
			Map<CFLibDbKeyHash256, ICFBamDelDepObj> map = new HashMap<CFLibDbKeyHash256,ICFBamDelDepObj>();
			allDelDep = map;
			ICFBamDelDep[] recList = schema.getCFBamBackingStore().getTableDelDep().readAllDerived( null );
			ICFBamDelDep rec;
			ICFBamDelDepObj obj;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamDelDepObj)schema.getScopeTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamDelDepObj realised = (ICFBamDelDepObj)obj.realise();
			}
		}
		int len = allDelDep.size();
		ICFBamDelDepObj arr[] = new ICFBamDelDepObj[len];
		Iterator<ICFBamDelDepObj> valIter = allDelDep.values().iterator();
		int idx = 0;
		while( ( idx < len ) && valIter.hasNext() ) {
			arr[idx++] = valIter.next();
		}
		if( idx < len ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				S_ProcName,
				0,
				"idx",
				idx,
				len );
		}
		else if( valIter.hasNext() ) {
			throw new CFLibArgumentOverflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
		}
		ArrayList<ICFBamDelDepObj> arrayList = new ArrayList<ICFBamDelDepObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamDelDepObj> cmp = new Comparator<ICFBamDelDepObj>() {
			@Override
			public int compare( ICFBamDelDepObj lhs, ICFBamDelDepObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFBamDelDepObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamDelDepObj> readCachedAllDelDep() {
		final String S_ProcName = "readCachedAllDelDep";
		ArrayList<ICFBamDelDepObj> arrayList = new ArrayList<ICFBamDelDepObj>();
		if( allDelDep != null ) {
			int len = allDelDep.size();
			ICFBamDelDepObj arr[] = new ICFBamDelDepObj[len];
			Iterator<ICFBamDelDepObj> valIter = allDelDep.values().iterator();
			int idx = 0;
			while( ( idx < len ) && valIter.hasNext() ) {
				arr[idx++] = valIter.next();
			}
			if( idx < len ) {
				throw new CFLibArgumentUnderflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
			}
			else if( valIter.hasNext() ) {
				throw new CFLibArgumentOverflowException( getClass(),
						S_ProcName,
						0,
						"idx",
						idx,
						len );
			}
			for( idx = 0; idx < len; idx ++ ) {
				arrayList.add( arr[idx] );
			}
		}
		Comparator<ICFBamDelDepObj> cmp = new Comparator<ICFBamDelDepObj>() {
			public int compare( ICFBamDelDepObj lhs, ICFBamDelDepObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public ICFBamDelDepObj readDelDepByIdIdx( CFLibDbKeyHash256 Id )
	{
		return( readDelDepByIdIdx( Id,
			false ) );
	}

	@Override
	public ICFBamDelDepObj readDelDepByIdIdx( CFLibDbKeyHash256 Id, boolean forceRead )
	{
		ICFBamDelDepObj obj = readDelDep( Id, forceRead );
		return( obj );
	}

	@Override
	public List<ICFBamDelDepObj> readDelDepByTenantIdx( CFLibDbKeyHash256 TenantId )
	{
		return( readDelDepByTenantIdx( TenantId,
			false ) );
	}

	@Override
	public List<ICFBamDelDepObj> readDelDepByTenantIdx( CFLibDbKeyHash256 TenantId,
		boolean forceRead )
	{
		final String S_ProcName = "readDelDepByTenantIdx";
		ICFBamScopeByTenantIdxKey key = schema.getCFBamBackingStore().getFactoryScope().newByTenantIdxKey();
		key.setRequiredTenantId( TenantId );
		Map<CFLibDbKeyHash256, ICFBamDelDepObj> dict;
		if( indexByTenantIdx == null ) {
			indexByTenantIdx = new HashMap< ICFBamScopeByTenantIdxKey,
				Map< CFLibDbKeyHash256, ICFBamDelDepObj > >();
		}
		if( ( ! forceRead ) && indexByTenantIdx.containsKey( key ) ) {
			dict = indexByTenantIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamDelDepObj>();
			ICFBamScopeObj obj;
			ICFBamScope[] recList = schema.getCFBamBackingStore().getTableScope().readDerivedByTenantIdx( null,
				TenantId );
			ICFBamScope rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamDelDepObj)schema.getScopeTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamDelDepObj realised = (ICFBamDelDepObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByTenantIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamDelDepObj arr[] = new ICFBamDelDepObj[len];
		Iterator<ICFBamDelDepObj> valIter = dict.values().iterator();
		int idx = 0;
		while( ( idx < len ) && valIter.hasNext() ) {
			arr[idx++] = valIter.next();
		}
		if( idx < len ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				S_ProcName,
				0,
				"idx",
				idx,
				len );
		}
		else if( valIter.hasNext() ) {
			throw new CFLibArgumentOverflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
		}
		ArrayList<ICFBamDelDepObj> arrayList = new ArrayList<ICFBamDelDepObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamDelDepObj> cmp = new Comparator<ICFBamDelDepObj>() {
			@Override
			public int compare( ICFBamDelDepObj lhs, ICFBamDelDepObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFBamDelDepObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamDelDepObj> readDelDepByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		return( readDelDepByDefSchemaIdx( DefSchemaId,
			false ) );
	}

	@Override
	public List<ICFBamDelDepObj> readDelDepByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId,
		boolean forceRead )
	{
		final String S_ProcName = "readDelDepByDefSchemaIdx";
		ICFBamDelDepByDefSchemaIdxKey key = schema.getCFBamBackingStore().getFactoryDelDep().newByDefSchemaIdxKey();
		key.setOptionalDefSchemaId( DefSchemaId );
		Map<CFLibDbKeyHash256, ICFBamDelDepObj> dict;
		if( indexByDefSchemaIdx == null ) {
			indexByDefSchemaIdx = new HashMap< ICFBamDelDepByDefSchemaIdxKey,
				Map< CFLibDbKeyHash256, ICFBamDelDepObj > >();
		}
		if( ( ! forceRead ) && indexByDefSchemaIdx.containsKey( key ) ) {
			dict = indexByDefSchemaIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamDelDepObj>();
			ICFBamDelDepObj obj;
			ICFBamDelDep[] recList = schema.getCFBamBackingStore().getTableDelDep().readDerivedByDefSchemaIdx( null,
				DefSchemaId );
			ICFBamDelDep rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamDelDepObj)schema.getScopeTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamDelDepObj realised = (ICFBamDelDepObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByDefSchemaIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamDelDepObj arr[] = new ICFBamDelDepObj[len];
		Iterator<ICFBamDelDepObj> valIter = dict.values().iterator();
		int idx = 0;
		while( ( idx < len ) && valIter.hasNext() ) {
			arr[idx++] = valIter.next();
		}
		if( idx < len ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				S_ProcName,
				0,
				"idx",
				idx,
				len );
		}
		else if( valIter.hasNext() ) {
			throw new CFLibArgumentOverflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
		}
		ArrayList<ICFBamDelDepObj> arrayList = new ArrayList<ICFBamDelDepObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamDelDepObj> cmp = new Comparator<ICFBamDelDepObj>() {
			@Override
			public int compare( ICFBamDelDepObj lhs, ICFBamDelDepObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFBamDelDepObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamDelDepObj> readDelDepByDelDepIdx( CFLibDbKeyHash256 RelationId )
	{
		return( readDelDepByDelDepIdx( RelationId,
			false ) );
	}

	@Override
	public List<ICFBamDelDepObj> readDelDepByDelDepIdx( CFLibDbKeyHash256 RelationId,
		boolean forceRead )
	{
		final String S_ProcName = "readDelDepByDelDepIdx";
		ICFBamDelDepByDelDepIdxKey key = schema.getCFBamBackingStore().getFactoryDelDep().newByDelDepIdxKey();
		key.setRequiredRelationId( RelationId );
		Map<CFLibDbKeyHash256, ICFBamDelDepObj> dict;
		if( indexByDelDepIdx == null ) {
			indexByDelDepIdx = new HashMap< ICFBamDelDepByDelDepIdxKey,
				Map< CFLibDbKeyHash256, ICFBamDelDepObj > >();
		}
		if( ( ! forceRead ) && indexByDelDepIdx.containsKey( key ) ) {
			dict = indexByDelDepIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamDelDepObj>();
			ICFBamDelDepObj obj;
			ICFBamDelDep[] recList = schema.getCFBamBackingStore().getTableDelDep().readDerivedByDelDepIdx( null,
				RelationId );
			ICFBamDelDep rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamDelDepObj)schema.getScopeTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamDelDepObj realised = (ICFBamDelDepObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByDelDepIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamDelDepObj arr[] = new ICFBamDelDepObj[len];
		Iterator<ICFBamDelDepObj> valIter = dict.values().iterator();
		int idx = 0;
		while( ( idx < len ) && valIter.hasNext() ) {
			arr[idx++] = valIter.next();
		}
		if( idx < len ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				S_ProcName,
				0,
				"idx",
				idx,
				len );
		}
		else if( valIter.hasNext() ) {
			throw new CFLibArgumentOverflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
		}
		ArrayList<ICFBamDelDepObj> arrayList = new ArrayList<ICFBamDelDepObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamDelDepObj> cmp = new Comparator<ICFBamDelDepObj>() {
			@Override
			public int compare( ICFBamDelDepObj lhs, ICFBamDelDepObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFBamDelDepObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public ICFBamDelDepObj readCachedDelDepByIdIdx( CFLibDbKeyHash256 Id )
	{
		ICFBamDelDepObj obj = null;
		obj = readCachedDelDep( Id );
		return( obj );
	}

	@Override
	public List<ICFBamDelDepObj> readCachedDelDepByTenantIdx( CFLibDbKeyHash256 TenantId )
	{
		final String S_ProcName = "readCachedDelDepByTenantIdx";
		ICFBamScopeByTenantIdxKey key = schema.getCFBamBackingStore().getFactoryScope().newByTenantIdxKey();
		key.setRequiredTenantId( TenantId );
		ArrayList<ICFBamDelDepObj> arrayList = new ArrayList<ICFBamDelDepObj>();
		if( indexByTenantIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamDelDepObj> dict;
			if( indexByTenantIdx.containsKey( key ) ) {
				dict = indexByTenantIdx.get( key );
				int len = dict.size();
				ICFBamDelDepObj arr[] = new ICFBamDelDepObj[len];
				Iterator<ICFBamDelDepObj> valIter = dict.values().iterator();
				int idx = 0;
				while( ( idx < len ) && valIter.hasNext() ) {
					arr[idx++] = valIter.next();
				}
				if( idx < len ) {
					throw new CFLibArgumentUnderflowException( getClass(),
						S_ProcName,
						0,
						"idx",
						idx,
						len );
				}
				else if( valIter.hasNext() ) {
					throw new CFLibArgumentOverflowException( getClass(),
							S_ProcName,
							0,
							"idx",
							idx,
							len );
				}
				for( idx = 0; idx < len; idx ++ ) {
					arrayList.add( arr[idx] );
				}
			}
		}
		else {
			ICFBamDelDepObj obj;
			Iterator<ICFBamDelDepObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamDelDepObj> cmp = new Comparator<ICFBamDelDepObj>() {
			@Override
			public int compare( ICFBamDelDepObj lhs, ICFBamDelDepObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public List<ICFBamDelDepObj> readCachedDelDepByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		final String S_ProcName = "readCachedDelDepByDefSchemaIdx";
		ICFBamDelDepByDefSchemaIdxKey key = schema.getCFBamBackingStore().getFactoryDelDep().newByDefSchemaIdxKey();
		key.setOptionalDefSchemaId( DefSchemaId );
		ArrayList<ICFBamDelDepObj> arrayList = new ArrayList<ICFBamDelDepObj>();
		if( indexByDefSchemaIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamDelDepObj> dict;
			if( indexByDefSchemaIdx.containsKey( key ) ) {
				dict = indexByDefSchemaIdx.get( key );
				int len = dict.size();
				ICFBamDelDepObj arr[] = new ICFBamDelDepObj[len];
				Iterator<ICFBamDelDepObj> valIter = dict.values().iterator();
				int idx = 0;
				while( ( idx < len ) && valIter.hasNext() ) {
					arr[idx++] = valIter.next();
				}
				if( idx < len ) {
					throw new CFLibArgumentUnderflowException( getClass(),
						S_ProcName,
						0,
						"idx",
						idx,
						len );
				}
				else if( valIter.hasNext() ) {
					throw new CFLibArgumentOverflowException( getClass(),
							S_ProcName,
							0,
							"idx",
							idx,
							len );
				}
				for( idx = 0; idx < len; idx ++ ) {
					arrayList.add( arr[idx] );
				}
			}
		}
		else {
			ICFBamDelDepObj obj;
			Iterator<ICFBamDelDepObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamDelDepObj> cmp = new Comparator<ICFBamDelDepObj>() {
			@Override
			public int compare( ICFBamDelDepObj lhs, ICFBamDelDepObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public List<ICFBamDelDepObj> readCachedDelDepByDelDepIdx( CFLibDbKeyHash256 RelationId )
	{
		final String S_ProcName = "readCachedDelDepByDelDepIdx";
		ICFBamDelDepByDelDepIdxKey key = schema.getCFBamBackingStore().getFactoryDelDep().newByDelDepIdxKey();
		key.setRequiredRelationId( RelationId );
		ArrayList<ICFBamDelDepObj> arrayList = new ArrayList<ICFBamDelDepObj>();
		if( indexByDelDepIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamDelDepObj> dict;
			if( indexByDelDepIdx.containsKey( key ) ) {
				dict = indexByDelDepIdx.get( key );
				int len = dict.size();
				ICFBamDelDepObj arr[] = new ICFBamDelDepObj[len];
				Iterator<ICFBamDelDepObj> valIter = dict.values().iterator();
				int idx = 0;
				while( ( idx < len ) && valIter.hasNext() ) {
					arr[idx++] = valIter.next();
				}
				if( idx < len ) {
					throw new CFLibArgumentUnderflowException( getClass(),
						S_ProcName,
						0,
						"idx",
						idx,
						len );
				}
				else if( valIter.hasNext() ) {
					throw new CFLibArgumentOverflowException( getClass(),
							S_ProcName,
							0,
							"idx",
							idx,
							len );
				}
				for( idx = 0; idx < len; idx ++ ) {
					arrayList.add( arr[idx] );
				}
			}
		}
		else {
			ICFBamDelDepObj obj;
			Iterator<ICFBamDelDepObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamDelDepObj> cmp = new Comparator<ICFBamDelDepObj>() {
			@Override
			public int compare( ICFBamDelDepObj lhs, ICFBamDelDepObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public void deepDisposeDelDepByIdIdx( CFLibDbKeyHash256 Id )
	{
		ICFBamDelDepObj obj = readCachedDelDepByIdIdx( Id );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public void deepDisposeDelDepByTenantIdx( CFLibDbKeyHash256 TenantId )
	{
		final String S_ProcName = "deepDisposeDelDepByTenantIdx";
		ICFBamDelDepObj obj;
		List<ICFBamDelDepObj> arrayList = readCachedDelDepByTenantIdx( TenantId );
		if( arrayList != null )  {
			Iterator<ICFBamDelDepObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeDelDepByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		final String S_ProcName = "deepDisposeDelDepByDefSchemaIdx";
		ICFBamDelDepObj obj;
		List<ICFBamDelDepObj> arrayList = readCachedDelDepByDefSchemaIdx( DefSchemaId );
		if( arrayList != null )  {
			Iterator<ICFBamDelDepObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeDelDepByDelDepIdx( CFLibDbKeyHash256 RelationId )
	{
		final String S_ProcName = "deepDisposeDelDepByDelDepIdx";
		ICFBamDelDepObj obj;
		List<ICFBamDelDepObj> arrayList = readCachedDelDepByDelDepIdx( RelationId );
		if( arrayList != null )  {
			Iterator<ICFBamDelDepObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public ICFBamDelDepObj updateDelDep( ICFBamDelDepObj Obj ) {
		ICFBamDelDepObj obj = Obj;
		schema.getCFBamBackingStore().getTableDelDep().updateDelDep( null,
			Obj.getDelDepRec() );
		if( Obj.getClassCode() == ((ICFBamSchemaObj)getSchema()).getDelDepTableObj().getClassCode() ) {
			obj = (ICFBamDelDepObj)Obj.realise();
		}
		obj.endEdit();
		return( obj );
	}

	@Override
	public void deleteDelDep( ICFBamDelDepObj Obj ) {
		ICFBamDelDepObj obj = Obj;
		schema.getCFBamBackingStore().getTableDelDep().deleteDelDep( null,
			obj.getDelDepRec() );
		Obj.forget();
	}

	@Override
	public void deleteDelDepByIdIdx( CFLibDbKeyHash256 Id )
	{
		ICFBamDelDepObj obj = readDelDep(Id);
		if( obj != null ) {
			ICFBamDelDepEditObj editObj = (ICFBamDelDepEditObj)obj.getEdit();
			boolean editStarted;
			if( editObj == null ) {
				editObj = (ICFBamDelDepEditObj)obj.beginEdit();
				if( editObj != null ) {
					editStarted = true;
				}
				else {
					editStarted = false;
				}
			}
			else {
				editStarted = false;
			}
			if( editObj != null ) {
				editObj.deleteInstance();
				if( editStarted ) {
					editObj.endEdit();
				}
			}
			obj.forget();
		}
		deepDisposeDelDepByIdIdx( Id );
	}

	@Override
	public void deleteDelDepByTenantIdx( CFLibDbKeyHash256 TenantId )
	{
		ICFBamScopeByTenantIdxKey key = schema.getCFBamBackingStore().getFactoryScope().newByTenantIdxKey();
		key.setRequiredTenantId( TenantId );
		if( indexByTenantIdx == null ) {
			indexByTenantIdx = new HashMap< ICFBamScopeByTenantIdxKey,
				Map< CFLibDbKeyHash256, ICFBamDelDepObj > >();
		}
		if( indexByTenantIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamDelDepObj> dict = indexByTenantIdx.get( key );
			schema.getCFBamBackingStore().getTableDelDep().deleteDelDepByTenantIdx( null,
				TenantId );
			Iterator<ICFBamDelDepObj> iter = dict.values().iterator();
			ICFBamDelDepObj obj;
			List<ICFBamDelDepObj> toForget = new LinkedList<ICFBamDelDepObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByTenantIdx.remove( key );
		}
		else {
			schema.getCFBamBackingStore().getTableDelDep().deleteDelDepByTenantIdx( null,
				TenantId );
		}
		deepDisposeDelDepByTenantIdx( TenantId );
	}

	@Override
	public void deleteDelDepByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		ICFBamDelDepByDefSchemaIdxKey key = schema.getCFBamBackingStore().getFactoryDelDep().newByDefSchemaIdxKey();
		key.setOptionalDefSchemaId( DefSchemaId );
		if( indexByDefSchemaIdx == null ) {
			indexByDefSchemaIdx = new HashMap< ICFBamDelDepByDefSchemaIdxKey,
				Map< CFLibDbKeyHash256, ICFBamDelDepObj > >();
		}
		if( indexByDefSchemaIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamDelDepObj> dict = indexByDefSchemaIdx.get( key );
			schema.getCFBamBackingStore().getTableDelDep().deleteDelDepByDefSchemaIdx( null,
				DefSchemaId );
			Iterator<ICFBamDelDepObj> iter = dict.values().iterator();
			ICFBamDelDepObj obj;
			List<ICFBamDelDepObj> toForget = new LinkedList<ICFBamDelDepObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByDefSchemaIdx.remove( key );
		}
		else {
			schema.getCFBamBackingStore().getTableDelDep().deleteDelDepByDefSchemaIdx( null,
				DefSchemaId );
		}
		deepDisposeDelDepByDefSchemaIdx( DefSchemaId );
	}

	@Override
	public void deleteDelDepByDelDepIdx( CFLibDbKeyHash256 RelationId )
	{
		ICFBamDelDepByDelDepIdxKey key = schema.getCFBamBackingStore().getFactoryDelDep().newByDelDepIdxKey();
		key.setRequiredRelationId( RelationId );
		if( indexByDelDepIdx == null ) {
			indexByDelDepIdx = new HashMap< ICFBamDelDepByDelDepIdxKey,
				Map< CFLibDbKeyHash256, ICFBamDelDepObj > >();
		}
		if( indexByDelDepIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamDelDepObj> dict = indexByDelDepIdx.get( key );
			schema.getCFBamBackingStore().getTableDelDep().deleteDelDepByDelDepIdx( null,
				RelationId );
			Iterator<ICFBamDelDepObj> iter = dict.values().iterator();
			ICFBamDelDepObj obj;
			List<ICFBamDelDepObj> toForget = new LinkedList<ICFBamDelDepObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByDelDepIdx.remove( key );
		}
		else {
			schema.getCFBamBackingStore().getTableDelDep().deleteDelDepByDelDepIdx( null,
				RelationId );
		}
		deepDisposeDelDepByDelDepIdx( RelationId );
	}
}